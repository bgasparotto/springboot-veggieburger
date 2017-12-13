package com.bgasparotto.springboot.veggieburger.persistence;

import java.io.Serializable;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Function;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;

import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseSetup;

/**
 * <p>
 * Abstract class containing basic tests for entities that are persisted through
 * a {@link JpaRepository}.
 * </p>
 * <p>
 * This abstraction can be used by JPA entities that have a {@code id} column
 * annotated with <strong>{@code javax.persistence.Id}</strong>. So far it has
 * been tested with id types of {@code Long}, but it might work as well with
 * other id types.
 * </p>
 * <p>
 * An example usage of this class for a given entity {@code Item} with an id of
 * type {@code Long}, containing five records on the testing database where none
 * of them have the id {@code 6} is given as follows:
 * </p>
 * <pre>
 * public class ItemRepositoryTest extends JpaRepositoryTest&lt;Item, Long&gt; {
 * 
 * 	public ItemRepositoryTest() {
 * 		super(Item::getId, Item::setId); //Id getter and setter
 * 	}
 * 
 * 	{@code @Override}
 * 	protected Item getUnpersistedEntity() {
 * 		return new Item("New Veggie Burger", 20,0);
 * 	}
 * 
 * 	{@code @Override}
 * 	protected Long getExistentEntityId() {
 * 		return 5L;
 * 	}
 * 
 * 	{@code @Override}
 * 	protected Long getNonExistentEntityId() {
 * 		return 6L;
 * 	}
 * }
 * </pre>
 * 
 * @author Bruno Gasparotto
 *
 * @param <T>
 *            The domain type the repository manages (your entity class)
 * @param <U>
 *            The type of the id of the entity the repository manages (your
 *            entity's id class)
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@TestExecutionListeners({
		DependencyInjectionTestExecutionListener.class,
		DbUnitTestExecutionListener.class })
@DatabaseSetup("/dbunit/dbunit-test-db.xml")
public abstract class JpaRepositoryTest<T, U extends Serializable> {
	
	private Function<? super T, ? extends U> getterMethod;
	private BiConsumer<? super T, ? super U> setterMethod;

	@Autowired
	protected JpaRepository<T, U> repository;
	
	/**
	 * 
	 * Constructor.
	 *
	 * @param getterMethod
	 *            The getter method for the {@code id}. E.g. {@code Item::getId}
	 * @param setterMethod
	 *            The setter method for the {@code id}. E.g. {@code Item::setId}
	 */
	public JpaRepositoryTest(
			Function<? super T, ? extends U> getterMethod,
			BiConsumer<? super T, ? super U> setterMethod) {
		
		this.getterMethod = getterMethod;
		this.setterMethod = setterMethod;
	}

	/**
	 * <p>
	 * Provide an {@code id} of an <strong>existent</strong> entity on the
	 * testing database.
	 * </p>
	 * <p>
	 * It's important that the provided {@code id} <strong>belongs to an entity
	 * that is allowed to be removed without provoking any foreign key
	 * constraint violations</strong>, otherwise, the tests relying on the given
	 * {@code id} are likely to fail.
	 * </p>
	 * 
	 * @return {@code Id} of an existent entity
	 */
	protected abstract U getExistentEntityId();
	
	/**
	 * <p>
	 * Provide an {@code id} of a nonexistent entity on database, in other
	 * words, an {@code id} that hasn't yet used for an entity of the same type
	 * on the database.
	 * </p>
	 * 
	 * @return {@code Id} of a nonexistent entity
	 */
	protected abstract U getNonExistentEntityId();

	/**
	 * <p>
	 * Provide an entity inexistent on the database, which means that its
	 * {@code id} is not present on the database.
	 * </p>
	 * <p>
	 * The other fields are allowed to have repeated values like those already
	 * present on the database, as long as they don't belong to any column with
	 * {@code UNIQUE} restriction.
	 * </p>
	 * 
	 * @return Entity inexistent on the persistence context
	 */
	protected abstract T getUnpersistedEntity();

	/**
	 * Provide an expected number of records of the the testing entity on
	 * database. The default value is <strong>5</strong>, override this method
	 * to change this value.
	 * 
	 * @return Expected number of records
	 */
	protected int getExpectedListSize() {
		return 5;
	}

	@Test
	public final void shouldFindOne() {
		U id = getExistentEntityId();

		T entity = repository.findOne(id);
		Assert.assertNotNull(entity);
		Assert.assertEquals(id.toString(), getterMethod.apply(entity).toString());
	}

	@Test(expected = InvalidDataAccessApiUsageException.class)
	public final void shouldThrowExceptionOnFindOneByNullId() {
		U nullId = null;
		repository.findOne(nullId);
	}

	@Test
	public final void shouldReturnNullForNonExistingPositiveId() {
		U nonExistingId = getNonExistentEntityId();

		T entity = repository.findOne(nonExistingId);
		Assert.assertNull(entity);
	}

	@Test
	public final void shouldFindAll() {
		List<T> entities = repository.findAll();
		Assert.assertEquals(getExpectedListSize(), entities.size());
	}

	@Test
	public final void shouldSave() {
		T entity = getUnpersistedEntity();
		setterMethod.accept(entity, null);

		T savedEntity = repository.save(entity);
		Assert.assertNotNull(savedEntity);
		Assert.assertNotNull(getterMethod.apply(entity));
	}
	
	@Test(expected = InvalidDataAccessApiUsageException.class)
	public final void shouldFailWhenTryingToSaveNull() {
		T nullEntity = null;
		repository.save(nullEntity);
	}

	@Test
	public final void shouldSaveEntityWithNotNullNonExistingId() {
		T entity = getUnpersistedEntity();
		U nonExistingId = getNonExistentEntityId();
		setterMethod.accept(entity, nonExistingId);

		T savedEntity = repository.save(entity);
		Assert.assertEquals(nonExistingId, getterMethod.apply(savedEntity));
	}

	@Test
	public final void shouldDeleteById() {
		U id = getExistentEntityId();

		Assert.assertNotNull(repository.findOne(id));

		repository.delete(id);
		Assert.assertNull(repository.findOne(id));
	}
	
	@Test(expected = InvalidDataAccessApiUsageException.class)
	public final void shouldNotDeleteNull() {
		T type = null;
		repository.delete(type);
	}
	
	@Test(expected = EmptyResultDataAccessException.class)
	public final void shouldThrowExceptionWhenDeleteNonExistingId() {
		repository.delete(getNonExistentEntityId());
	}
}