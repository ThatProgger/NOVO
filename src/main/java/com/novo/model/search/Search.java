package com.novo.model.search;

import com.novo.model.jobtypes.JobType;
import com.novo.model.responsible.Responsible;
import com.novo.model.user.User;

import java.util.ArrayList;
import java.util.List;

/**
 * This model allows you to search the database for given parameters.
 *
 * @author Mikhail Dedyukhin
 * @since 1.0
 */
public class Search {
    private String words;
    private List<Responsible> responsibleList;
    private List<User> usersList;
    private List<JobType> jobTypesList;



}
