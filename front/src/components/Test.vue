<template>
    <div class="page">
        <button @click="connectSrv">Link</button>
        <button @click="disconnect">UnLink</button>
        <button @click="send">send</button>
        <input type="text" v-model="val">
        </div>
    </template>

    <script>
  export default {
    data() {
      return {
        invokeIdCnt: 0,
        val: ''
      }
    },
    methods: {
      getInvokeId() {
        let hex = (this.invokeIdCnt++).toString(16);
        var zero = '0000';
        var tmp = 4 - hex.length;
        return zero.substr(0, tmp) + hex;
      },
      onFailed(frame) {
        console.log('Connection failure: '+ JSON.stringify (frame));},ResponseCallback (frame) {Console.log ("receive information"+ frame.body);},Disconnect () {This.disconnetWM ();},OnConnectEd (frame) {Console.log ('connection success:' + frame);This.$stompClient.subscribe ('/echo', this).responseCallback, this.onFailed);},ConnectSrv () {This.connetWM ("http://localHost:3001/echo, {}, this.onConnected, this.onFailed);},Send () {Let destinatiOn ='/echo'Let invokeId = this.getInvokeId ();Let body = this.valThis.senDWM (destination, body, invokeId, this.responseCallback, 3000);},},StompClient: {MonitorIntervalTime: 100,StompReconnect: false,Timeout (orgCmd) {}}};</script>