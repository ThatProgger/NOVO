package com.novo.pagination;

import java.util.List;

/**
 * This interface allows you to create implementations for generating pagination
 * @author Mikhail Dedyukhin
 * @since 1.0
 */
public interface Pagination {
    /**
     * Sets the value of the current page.
     * @param currentPage must not be less than zero
     */
    public void setCurrentPage (int currentPage);

    /**
     * Sets the value of the total pages number
     * @param totalPages must not be less than zero
     */
    public void setTotalPages (int totalPages);

    /**
     * Returns the correct style depending on the current page.
     * @return Previous button style.
     */
    public String getPreviousButtonStyle();

    /**
     * Returns the correct style depending on the current page.
     * @return Next button style.
     */
    public String getNextStyle ();

    /**
     * Allows you to create a link to the previous number
     * @return previous link
     */
    public String getPreviousLink ();

    /**
     * Allows you to create a link to the next number
     * @return next link
     */
    public String getNextLink ();

    /**
     * Creates and returns a pagination list.
     * @return Collection of {@link PaginationItem} interfaces.
     */
    public List<PaginationItem> paginationItemList ();
}
