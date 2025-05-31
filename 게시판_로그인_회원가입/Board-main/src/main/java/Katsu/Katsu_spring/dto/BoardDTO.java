package Katsu.Katsu_spring.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class BoardDTO {
    private int postId;
    private int userId;
    private String title;
    private String content;
    private String createdAt;
    private int viewCnt;
    private int likes;
}
