package com.novo.pagination;

/**
 * Allows you to create implementations to handle the page number
 * @author Mikhail Dedyukhin
 */
public interface PaginationItem {

    /**
     * Sets the number of page.
     * @param pageNumber must not be less than zero or greater than total number of pages.
     */
    public void setPageNumber (int pageNumber);

    /**
     * Marks that this page is active.
     * @param active true if the page is active or false if not.
     */
    public void setActivePage (boolean active);

    /**
     * Sets the page as a separator.
     * @param separator true if the page is separator or false if not.
     */
    public void setPageAsSeparator (boolean separator);

    /**
     * Returns the required style depending on the attributes
     * @return CSS style
     */
    public String getStyle ();

    /**
     * Returns a link or a stub depending on the attributes.
     * @return the link.
     */
    public String getLink ();

    /**
     * Returns either the page number or the separator character, depending on the attributes.
     * @return
     */
    public String getPageNumberOrSeparator ();

}
