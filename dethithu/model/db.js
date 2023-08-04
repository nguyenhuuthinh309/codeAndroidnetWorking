const mongoose = require('mongoose');
mongoose.connect('mongodb+srv://thinh309:thinh3092003@cluster0.adfcye6.mongodb.net/asm?retryWrites=true&w=majority').catch((err) => {
    console.log(err);
});

module.exports = {mongoose};