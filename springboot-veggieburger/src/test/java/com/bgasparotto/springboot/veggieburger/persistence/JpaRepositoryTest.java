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

@RunWith(SpringRunner.class)
@SpringBootTest
@TestExecutionListeners({
		DependencyInjectionTestExecutionListener.class,
		DbUnitTestExecutionListener.class })
@DatabaseSetup("/dbunit/dbunit-test-db.xml")
public abstract class JpaRepositoryTest<T, U extends Serializable> {
	
	private Function<? super T, ? extends U> idExtractor;
	private BiConsumer<? super T, ? super U> idConsumer;

	@Autowired
	protected JpaRepository<T, U> repository;
	
	public JpaRepositoryTest(
			Function<? super T, ? extends U> idExtractor,
			BiConsumer<? super T, ? super U> idConsumer) {
		
		this.idExtractor = idExtractor;
		this.idConsumer = idConsumer;
	}

	/**
	 * Provide an {@code id} of an existent entity on database.
	 * 
	 * @return {@code Id} of an existent entity
	 */
	protected abstract U getExistentEntityId();
	
	/**
	 * Provide an {@code id} of a nonexistent entity on database.
	 * 
	 * @return {@code Id} of a nonexistent entity
	 */
	protected abstract U getUNonexistentEntityId();

	/**
	 * Provide an entity inexistent on the persistence context.
	 * 
	 * @return Entity inexistent on the persistence context
	 */
	protected abstract T getUnpersistedEntity();

	/**
	 * Provide a expected number of records of the the testing entity on
	 * database. The default is <strong>5</strong>.
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
		Assert.assertEquals(id.toString(), idExtractor.apply(entity).toString());
	}

	@Test(expected = InvalidDataAccessApiUsageException.class)
	public final void shouldThrowExceptionOnFindOneByNullId() {
		U nullId = null;
		repository.findOne(nullId);
	}

	@Test
	public final void shouldReturnNullForNonExistingPositiveId() {
		U nonExistingId = getUNonexistentEntityId();

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
		idConsumer.accept(entity, null);

		T savedEntity = repository.save(entity);
		Assert.assertNotNull(savedEntity);
		Assert.assertNotNull(idExtractor.apply(entity));
	}
	
	@Test(expected = InvalidDataAccessApiUsageException.class)
	public final void shouldFailWhenTryingToSaveNull() {
		T nullEntity = null;
		repository.save(nullEntity);
	}

	@Test
	public final void shouldSaveEntityWithNotNullNonExistingId() {
		T entity = getUnpersistedEntity();
		U nonExistingId = getUNonexistentEntityId();
		idConsumer.accept(entity, nonExistingId);

		T savedEntity = repository.save(entity);
		Assert.assertEquals(nonExistingId, idExtractor.apply(savedEntity));
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
		repository.delete(getUNonexistentEntityId());
	}
}