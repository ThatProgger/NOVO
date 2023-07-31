package com.novo.pagination.impl;

import com.novo.pagination.PaginationItem;

/**
 * This class implements {@link PaginationItem} interface
 *
 * @author Mikhail Dedyukhin
 * @since 1.0
 */
public class PaginationItemImpl implements PaginationItem {

    private int pageNumber = 0;
    private boolean active = false;
    private boolean separator = false;

    private final String style = "page-link";
    private final String styleActive = "page-link active";
    private final String linkActive = "entries?page=%d";
    private final String linkStub = "#";


    @Override
    public void setPageNumber(int pageNumber) {
        if (pageNumber < 0)
            throw new IllegalArgumentException("The page number less than zero");
        this.pageNumber = pageNumber;
    }

    @Override
    public void setActivePage(boolean active) {
        this.active = active;
    }

    @Override
    public void setPageAsSeparator(boolean separator) {
        this.separator = separator;
    }

    @Override
    public String getStyle() {
        if(active == true && separator == false)
            return styleActive;
        return style;
    }


    @Override
    public String getPageNumberOrSeparator() {
        if(separator == false){
            return String.valueOf(pageNumber + 1);
        } else {
            return "...";
        }
    }

    @Override
    public String getLink() {
        if(separator == false)
            return String.format(linkActive, pageNumber);
        else
        return linkStub;
    }
}
