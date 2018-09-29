import Vue from 'vue';
import MonitorApp from './components/MonitorApp.vue';

Vue.component('monitor-app', MonitorApp);

let monitorApp = new Vue({
  el: '#monitor',
  data: {
	  incidences: []
  }
});



