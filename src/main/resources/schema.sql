CREATE TABLE exams (
    id BIGINT PRIMARY KEY,
    title VARCHAR(120) NOT NULL,
    description VARCHAR(500) NOT NULL
);

CREATE TABLE questions (
    id BIGINT PRIMARY KEY,
    exam_id BIGINT NOT NULL,
    question_text VARCHAR(500) NOT NULL,
    option_a VARCHAR(300) NOT NULL,
    option_b VARCHAR(300) NOT NULL,
    option_c VARCHAR(300) NOT NULL,
    option_d VARCHAR(300) NOT NULL,
    correct_option VARCHAR(1) NOT NULL,
    FOREIGN KEY (exam_id) REFERENCES exams(id)
);