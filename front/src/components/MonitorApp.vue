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
