<template>
	<div>
		<nav class="navbar is-white topNav">
			<div class="container">
				<div class="navbar-brand">
					<a class="navbar-item" href="../">
						
					</a>
					<div class="navbar-burger burger" data-target="topNav">
						<span></span>
						<span></span>
						<span></span>
					</div>
				</div>
				<div id="topNav" class="navbar-menu">
					<div class="navbar-start">
						<span class="tag is-success" v-if="connected">En línea</span>
						<span class="tag is-danger" v-if="!connected">Fuera de línea</span>
					</div>
					<div class="navbar-end">
						
					</div>
				</div>
			</div>
		</nav>
		
		<section class="container">
			<div class="columns">
			
				<div class="column is-offset-1 is-10">
					<div class="box content">
						<span v-if="incidencesError">Error al obtener incidencias</span>
						<article v-if="!incidencesError" v-for="incidence in incidences" :key="incidence.id" :class="['post', incidence.hot ?  'hot' : '']">
							<h4 class="incidence-title">{{incidence.title}}</h4>
							<div class="media">
								<div class="media-left">
									<p class="image is-32x32">
										<img src="http://bulma.io/images/placeholders/128x128.png">
									</p>
								</div>
								<div class="media-content">
									<div class="content">
										<p>
											<a href="#">{{incidence.operator.name}}</a> &nbsp;
											<span class="tag is-warning" v-if="incidence.type === 'WARNING'">{{incidence.type}}</span>
											<span class="tag is-danger" v-if="incidence.type === 'ERROR'">{{incidence.type}}</span>
											<span class="tag is-info" v-if="incidence.type === 'INFO'">{{incidence.type}}</span>
										</p>
									</div>
								</div>
								<div class="media-right" v-if="incidence.resolved">
									<button class="button is-success" type="button">Resuelta</button>
								</div>
								<div class="media-right" v-if="!incidence.resolved">
									<button class="button is-info" type="button" @click="resolve(incidence)">Resolver</button>
								</div>
							</div>
						</article>
						
					</div>
				</div>
			</div>
		</section>
		<footer class="footer">
			<div class="container">
				<div class="content has-text-centered">
				
				</div>
			</div>
		</footer>


	</div>
</template>
<script>

import Vue from 'vue';
import axios from 'axios';
import SockJS from "sockjs-client";
import Stomp from "webstomp-client";

let stompClient = null;

export default {
	data() {
		return {
			incidences: [],
			incidencesError: null,
			connected: false
		};
	},	
	mounted() {
		let self = this;
		return axios.get('http://localhost:8080/incidences2')
			.then(function (response) {
				self.incidences = response.data;
				self.connect();
			})
			.catch(function (error) {
				console.log(error);
				self.incidencesError = true;
			});
	},
	methods: {
		connect() {
			console.log("Conectando a WebSocket...")
			this.socket = new SockJS("http://localhost:8080/ws");
			this.stompClient = Stomp.over(this.socket);
			this.stompClient.connect({}, this.onConnected, this.onConnectError);
		},
		onConnected(frame) {
		  this.connected = true;
		  this.subscribe();
		},
		onConnectError(error) {
			console.log('Error de conexión: %s', error);
		},
		subscribe() {
			this.stompClient.subscribe("/topic/incidence", msg => {
				let incidence = JSON.parse(msg.body);
				incidence.hot = true;
				this.incidences.unshift(incidence);
			});
		},
		resolve(incidence) {
			let self = this;
			return axios.put(`http://localhost:8080/incidences2/${incidence.id}`)
				.then(function (response) {
					incidence.resolved = true;
				})
				.catch(function (error) {
					
				});
		}
	}
}
</script>
<style>
html,body {
  font-family: 'Open Sans', serif;
  background: #F2F6FA;
}
footer {
  background-color: #F2F6FA !important;
}
.topNav {
  border-top: 5px solid #3498DB;
}
.topNav .container {
  border-bottom: 1px solid #E6EAEE;
}
.container .columns {
  margin: 3rem 0;
}
.navbar-menu .navbar-item {
  padding: 0 2rem;
}
aside.menu {
  padding-top: 3rem;
}
aside.menu .menu-list {
  line-height: 1.5;
}
aside.menu .menu-label {
  padding-left: 10px;
  font-weight: 700;
}
.button.is-primary.is-alt {
  background: #00c6ff;
  background: -webkit-linear-gradient(to bottom, #0072ff, #00c6ff);
  background: linear-gradient(to bottom, #0072ff, #00c6ff);
  font-weight: 700;
  font-size: 14px;
  height: 3rem;
  line-height: 2.8;
}
.media-left img {
  border-radius: 50%;
}
.media-content p {
  font-size: 14px;
  line-height: 2.3;
  font-weight: 700;
  color: #8F99A3;
}
article.post {
  margin: 1rem;
  padding-bottom: 1rem;
  border-bottom: 1px solid #E6EAEE;
}
article.post:last-child {
  padding-bottom: 0;
  border-bottom: none;
}
.menu-list li{
  padding: 5px;
}

.hot .incidence-title {
	-webkit-animation-name: example; /* Safari 4.0 - 8.0 */
    -webkit-animation-duration: 4s; /* Safari 4.0 - 8.0 */
	-webkit-animation-iteration-count: infinite;
    animation-name: example;
    animation-duration: 1s;
	animation-iteration-count: infinite;
}

/* Safari 4.0 - 8.0 */
@-webkit-keyframes example {
    50%  {color: brown;}
}

/* Standard syntax */
@keyframes example {
     50%  {color: brown;}
}

</style>
