// init-mongo.js
// Create a custom database and user when Mongo starts (first run)

db = db.getSiblingDB('replicatedexpense');   // <-- name of your DB

db.createUser({
  user: "appuser",
  pwd: "appuser",
  roles: [
    { role: "readWrite", db: "replicatedexpense" }
  ]
});

// optional: create a collection so DB is physically created
db.createCollection("sampleTest");

