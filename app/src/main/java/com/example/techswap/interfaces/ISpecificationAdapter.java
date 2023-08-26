package com.example.techswap.interfaces;

/**
 * The `ISpecificationAdapter` interface defines a method for determining the count of specifications in an adapter.
 * Implementations of this interface are intended to provide functionality for managing and displaying specifications.
 */
public interface ISpecificationAdapter {

    /**
     * Returns the number of specifications available in the adapter.
     *
     * @return The number of specifications.
     */
    int getItemCount();

}
