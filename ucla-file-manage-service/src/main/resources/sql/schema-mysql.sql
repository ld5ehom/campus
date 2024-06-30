CREATE TABLE session_files
(
    file_id    INT AUTO_INCREMENT PRIMARY KEY COMMENT 'Unique identifier for the file.',
    session_id INT          NOT NULL COMMENT 'Identifier for the session to which the file belongs.',
    file_name  VARCHAR(255) NOT NULL COMMENT 'Name of the stored file.',
    file_type  VARCHAR(50)  NOT NULL COMMENT 'Type of the file (e.g., mp4).',
    file_path  VARCHAR(255) NOT NULL COMMENT 'Path where the file is stored on the server.',
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP COMMENT 'Date and time when the file was first created.',
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT 'Date and time when the file information was last updated.'
);

