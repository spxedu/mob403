var express = require('express');
var router = express.Router();
const socketapi = require('../socket_io');
/* GET users listing. */
router.get('/', function(req, res, next) {

  socketapi.io.emit("new message", {username: "server xxx", message: "home page"})

  res.send('respond with a resource');
});

module.exports = router;
