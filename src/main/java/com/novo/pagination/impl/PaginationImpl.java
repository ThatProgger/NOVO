package com.novo.pagination.impl;

import com.novo.pagination.Pagination;
import com.novo.pagination.PaginationItem;

import java.util.LinkedList;
import java.util.List;

/**
 * This class implements {@link Pagination} interface
 *
 * @author Mikhail Dedyukhin
 * @since 1.0
 */
public class PaginationImpl implements Pagination {

    private int currentPage = 0;
    private int totalPages = 0;
    private final String link = "entries?page=%d";
    private final String style = "page-item";
    private final String styleDisabled = "page-item disabled";


    @Override
    public void setCurrentPage(int currentPage) {
        if (currentPage < 0)
            throw new RuntimeException("The current page number is less than zero");

        this.currentPage = currentPage;
    }

    @Override
    public void setTotalPages(int totalPages) {
        if (totalPages < 0)
            throw new RuntimeException("The total page number is less than zero");

        this.totalPages = totalPages;
    }

    @Override
    public String getPreviousButtonStyle() {
        if (currentPage == 0) {
            return styleDisabled;
        } else {
            return style;
        }
    }

    @Override
    public String getNextStyle() {
        if (currentPage == totalPages - 1) {
            return styleDisabled;
        } else {
            return style;
        }
    }

    @Override
    public List<PaginationItem> paginationItemList() {
        List<PaginationItem> list = new LinkedList<>();



        if (totalPages >= 5) {
            if (currentPage == 0) {
                PaginationItem paginationItem = new PaginationItemImpl();
                paginationItem.setPageNumber(0);
                paginationItem.setActivePage(true);
                paginationItem.setPageAsSeparator(false);
                list.add(paginationItem);
            } else {
                PaginationItem paginationItem = new PaginationItemImpl();
                paginationItem.setPageNumber(0);
                paginationItem.setActivePage(false);
                paginationItem.setPageAsSeparator(false);
                list.add(paginationItem);
            }

            if (currentPage + 3 < totalPages) {
                if (currentPage < 3) {
                    for (int i = 1; i < currentPage + 3; i++) {
                        if (i == currentPage) {
                            PaginationItem paginationItem = new PaginationItemImpl();
                            paginationItem.setPageNumber(i);
                            paginationItem.setActivePage(true);
                            paginationItem.setPageAsSeparator(false);
                            list.add(paginationItem);
                        } else {
                            PaginationItem paginationItem = new PaginationItemImpl();
                            paginationItem.setPageNumber(i);
                            paginationItem.setActivePage(false);
                            paginationItem.setPageAsSeparator(false);
                            list.add(paginationItem);
                        }
                    }

                    list.add(getSeparator());
                } else {
                    list.add(getSeparator());
                    for (int i = currentPage - 2; i < currentPage + 3; i++) {
                        if (i == currentPage) {
                            PaginationItem paginationItem = new PaginationItemImpl();
                            paginationItem.setPageNumber(i);
                            paginationItem.setActivePage(true);
                            paginationItem.setPageAsSeparator(false);
                            list.add(paginationItem);
                        } else {
                            PaginationItem paginationItem = new PaginationItemImpl();
                            paginationItem.setPageNumber(i);
                            paginationItem.setActivePage(false);
                            paginationItem.setPageAsSeparator(false);
                            list.add(paginationItem);
                        }
                    }

                    list.add(getSeparator());
                }
            } else {
                list.add(getSeparator());
                for (int i = currentPage - 3; i < totalPages - 1; i++) {

                    if (i == currentPage) {
                        PaginationItem paginationItem = new PaginationItemImpl();
                        paginationItem.setPageNumber(i);
                        paginationItem.setActivePage(true);
                        paginationItem.setPageAsSeparator(false);
                        list.add(paginationItem);
                    } else {
                        PaginationItem paginationItem = new PaginationItemImpl();
                        paginationItem.setPageNumber(i);
                        paginationItem.setActivePage(false);
                        paginationItem.setPageAsSeparator(false);
                        list.add(paginationItem);
                    }
                }
            }

            if (currentPage == totalPages - 1) {
                PaginationItem paginationItem = new PaginationItemImpl();
                paginationItem.setPageNumber(totalPages - 1);
                paginationItem.setActivePage(true);
                paginationItem.setPageAsSeparator(false);
                list.add(paginationItem);
            } else {
                PaginationItem paginationItem = new PaginationItemImpl();
                paginationItem.setPageNumber(totalPages - 1);
                paginationItem.setActivePage(false);
                paginationItem.setPageAsSeparator(false);
                list.add(paginationItem);
            }
        } else {
            for (int i = 0; i < totalPages; i++) {
                if (i == currentPage) {
                    PaginationItem paginationItem = new PaginationItemImpl();
                    paginationItem.setPageNumber(i);
                    paginationItem.setActivePage(true);
                    paginationItem.setPageAsSeparator(false);
                    list.add(paginationItem);
                } else {
                    PaginationItem paginationItem = new PaginationItemImpl();
                    paginationItem.setPageNumber(i);
                    paginationItem.setActivePage(false);
                    paginationItem.setPageAsSeparator(false);
                    list.add(paginationItem);
                }
            }
        }

        return list;
    }

    @Override
    public String getPreviousLink() {
        if (currentPage == 0)
            return "#";
        else {
            return String.format(link, currentPage - 1);
        }
    }

    @Override
    public String getNextLink() {
        if (currentPage == totalPages - 1)
            return "#";
        else {
            return String.format(link, currentPage + 1);
        }
    }


    private PaginationItem getSeparator() {
        PaginationItem separator = new PaginationItemImpl();
        separator.setPageNumber(0);
        separator.setActivePage(false);
        separator.setPageAsSeparator(true);
        return separator;
    }
}
