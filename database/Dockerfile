# Use postgres:latest as the base image
FROM postgres:latest

# Set environment variables for the database name, user, and password
ENV POSTGRES_DB=mydatabase
ENV POSTGRES_USER=myuser
ENV POSTGRES_PASSWORD=mypassword

# Copy the init.sql script to the container
COPY init.sql /docker-entrypoint-initdb.d/

# Expose port 5432
EXPOSE 5432
