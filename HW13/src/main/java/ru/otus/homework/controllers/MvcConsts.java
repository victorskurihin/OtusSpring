package ru.otus.homework.controllers;

public class MvcConsts {
    static final String VIEW_NAME_BOOKS_LIST = "books_list.html";
    static final String VIEW_NAME_BOOK_DETAILS = "book_details.html";
    static final String VIEW_NAME_LOGIN = "login.html";

    public static final String REQUEST_BOOK_DETAILS = "/book_details";
    public static final String REQUEST_LOGIN = "/login";
    public static final String REQUEST_LOGIN_PROCESSING = "/login_processing";

    public static final String REST_MAPPING_BOOK = "/book/";
    static final String REST_MAPPING_BOOK_WITH_ID = REST_MAPPING_BOOK + "{id}";

    public static final String REST_MAPPING_COMMENT = "/comment/";
    static final String REST_MAPPING_COMMENT_WITH_ID = REST_MAPPING_COMMENT + "{id}";

    static final String MODEL_ATTR_BOOK = "book";





}
