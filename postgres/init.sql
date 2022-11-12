SELECT 'CREATE DATABASE arch_spring'
WHERE NOT EXISTS (SELECT FROM pg_database WHERE datname = 'arch_spring')\gexec

\c arch_spring;

CREATE TABLE key_value (
    key TEXT PRIMARY KEY,
    value TEXT NOT NULL
);
