// Cài đặt socket .io bằng lệnh: npm i socket.io

const io = require( "socket.io" )();
const socketapi = {
    io: io
};
 

io.on('connection', client => {
    console.log("socket connect .... ");
  
    client.on('new message', data => { 
      console.log(data);
  
      let xxx = {
        username: 'abc',
        message: 'noi dung gui tu server ' + data
      }
      client.emit("new message",  xxx);
    });
  
   
  
    client.on('disconnect', () => { 
      console.log('Socket disconnect');
  
    });
  });

module.exports = socketapi;