CREATE TABLE playback_records
(
    record_id  INT AUTO_INCREMENT PRIMARY KEY COMMENT 'Unique playback identifier',
    user_id    INT    NOT NULL COMMENT 'User identifier, references user_id in users table',
    file_id    INT    NOT NULL COMMENT 'Playback file identifier',
    start_time TIMESTAMP NOT NULL COMMENT 'Playback start time (UNIX timestamp in milliseconds)',
    end_time   TIMESTAMP COMMENT 'Playback end time (UNIX timestamp in milliseconds)'
) COMMENT ='Table storing playback activity data. Each record includes information about the period a user plays a file.';

CREATE TABLE event_logs
(
    event_id   INT AUTO_INCREMENT PRIMARY KEY COMMENT 'Unique event log identifier',
    record_id  INT NOT NULL COMMENT 'Related playback session identifier, references record_id in playback_records table',
    user_id    INT NOT NULL COMMENT 'User identifier, references user_id in users table',
    event_type VARCHAR(50)  NOT NULL COMMENT 'Event type (e.g., "play", "pause", "stop")',
    timestamp  TIMESTAMP       NOT NULL COMMENT 'Event occurrence time (UNIX timestamp in milliseconds)',
    FOREIGN KEY (record_id) REFERENCES playback_records (record_id)
) COMMENT ='Table storing event log data. Logs various events occurring during playback.';
