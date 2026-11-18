INSERT INTO exams (id, title, description) VALUES
    (1, 'Java Basics', 'Test your knowledge of core Java concepts.'),
    (2, 'Web Development', 'A short quiz about web technologies.');

INSERT INTO questions (id, exam_id, question_text, option_a, option_b, option_c, option_d, correct_option) VALUES
    (1, 1, 'What keyword is used to create a subclass in Java?', 'this', 'extends', 'implements', 'super', '2'),
    (2, 1, 'Which type stores true or false values?', 'int', 'String', 'boolean', 'char', '2'),
    (3, 1, 'Which method is the usual entry point of a Java application?', 'start()', 'run()', 'init()', 'main()', '3'),
    (4, 1, 'Which collection does not allow duplicate elements?', 'List', 'Set', 'ArrayList', 'Queue', '1'),
    (5, 1, 'What is the result of 5 / 2 when both values are integers?', '2', '2.5', '3', 'An error', '0'),
    (6, 2, 'Which language is used to style web pages?', 'HTML', 'SQL', 'CSS', 'Java', '2'),
    (7, 2, 'What does HTML stand for?', 'HyperText Markup Language', 'High Transfer Machine Link', 'Home Tool Markup Language', 'Hyperlink Text Management Language', '0'),
    (8, 2, 'Which HTTP method is commonly used to retrieve a page?', 'POST', 'GET', 'PATCH', 'DELETE', '1'),
    (9, 2, 'Which tag creates a hyperlink in HTML?', '<link>', '<href>', '<a>', '<url>', '2'),
    (10, 2, 'Which CSS property changes text color?', 'font-style', 'background', 'text-size', 'color', '2');