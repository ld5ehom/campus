CREATE TABLE COURSE_RATINGS
(
    rating_id  INT      NOT NULL     AUTO_INCREMENT COMMENT 'Rating ID',
    course_id  INT      NOT NULL COMMENT 'Course ID, COURSES 테이블 참조',
    user_id    INT      NOT NULL COMMENT 'Comment User ID',
    rating     TINYINT  NOT NULL COMMENT 'Rating (1-5)',
    comment    TEXT     NULL     COMMENT 'User Comment',
    created_at DATETIME NULL     DEFAULT CURRENT_TIMESTAMP COMMENT '레코드 생성 시간',
    PRIMARY KEY (rating_id)
) COMMENT 'Course Rating Table';

CREATE TABLE COURSE_SESSIONS
(
    session_id INT          NOT NULL     AUTO_INCREMENT COMMENT 'session ID',
    course_id  INT          NOT NULL COMMENT '해당 세션이 속한 강의의 ID, COURSES 테이블 참조',
    title      VARCHAR(255) NOT NULL COMMENT 'session title',
    PRIMARY KEY (session_id)
) COMMENT 'Course Session Table';

CREATE TABLE COURSES
(
    course_id     INT          NOT NULL     AUTO_INCREMENT COMMENT '강의의 고유 식별자',
    title         VARCHAR(255) NOT NULL COMMENT '강의 제목',
    description   TEXT         NULL     COMMENT '강의에 대한 자세한 설명',
    professor_id INT          NOT NULL COMMENT '강사의 식별자, 외래 키로 사용될 수 있음',
    created_at    DATETIME     NULL     DEFAULT CURRENT_TIMESTAMP COMMENT '강의 생성 시간',
    PRIMARY KEY (course_id)
) COMMENT '강의 기본 정보를 저장하는 테이블';

ALTER TABLE COURSE_SESSIONS
    ADD CONSTRAINT FK_COURSES_TO_COURSE_SESSIONS
        FOREIGN KEY (course_id)
            REFERENCES COURSES (course_id);

ALTER TABLE COURSE_RATINGS
    ADD CONSTRAINT FK_COURSES_TO_COURSE_RATINGS
        FOREIGN KEY (course_id)
            REFERENCES COURSES (course_id);