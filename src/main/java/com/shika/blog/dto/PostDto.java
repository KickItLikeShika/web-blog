package com.shika.blog.dto;

public class PostDto {

    private long id;
    private String content;
    private String title;
    private String username;

    public PostDto() {}

    public PostDto(long id, String content, String title, String username) {
        this.id = id;
        this.content = content;
        this.title = title;
        this.username = username;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
