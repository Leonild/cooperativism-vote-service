CREATE TABLE IF NOT EXISTS voting_agenda (
  voting_agenda_id LONG NOT NULL,
  description varchar(2000) NOT NULL,
  PRIMARY KEY (voting_agenda_id)
);

CREATE TABLE IF NOT EXISTS voting_session (
  voting_session_id LONG NOT NULL,
  voting_agenda_id LONG NOT NULL,
  PRIMARY KEY (voting_session_id)
);

CREATE TABLE IF NOT EXISTS vote (
  vote_id LONG NOT NULL,
  voting_agenda_id LONG NOT NULL,
  cpf varchar(20) NOT NULL,
  choice varchar(5) NOT NULL,
  vote bool NOT NULL,
  PRIMARY KEY (vote_id)
);