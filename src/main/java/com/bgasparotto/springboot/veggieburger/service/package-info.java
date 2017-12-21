/**
 * <p>
 * Main package of {@code service} layer. This package contains the service
 * abstractions and implementations. This package may be reorganised into
 * sub-packages in order to provide a finer granularity as soon as its amount of
 * types grow.
 * </p>
 * <p>
 * The service layer is where all the business rules should be applied, this
 * way, the corresponding resource of persistence layer is left untouched and
 * the changes don't affect other services using that single resource. The
 * resources from the persistence layer should only be accessed by the
 * {@code web} layer directly if they don't need any changes because of business
 * rules.
 * </p>
 * <p>
 * The service layer is where all the business rules should be applied, this
 * way, the corresponding resource of persistence layer is left untouched and
 * the changes don't affect other services using that single resource.
 * </p>
 * <p>
 * The service layer should access resources from the {@code model} and
 * {@code persistence} layers, but shouldn't use resources from {@code web}
 * layer, even if it's possible to import or reference these resources.
 * </p>
 */
package com.bgasparotto.springboot.veggieburger.service;