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
					
					</div>
					<div class="navbar-end">
						
					</div>
				</div>
			</div>
		</nav>
		
		<section class="container">
			<div class="columns">
				<div class="column is-3">
					<span class="button is-primary is-block is-alt is-large">Operarios</span>
					<aside class="menu">
						
					</aside>
				</div>
				<div class="column is-9">
					<div class="box content">
						<span v-if="incidencesError">Error al obtener incidencias</span>
						<article v-if="!incidencesError" v-for="incidence in incidences" :key="incidence.id" class="post">
							<h4>{{incidence.title}}</h4>
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
											<span class="tag">{{incidence.type}}</span>
										</p>
									</div>
								</div>
								<div class="media-right">
									<span class="has-text-grey-light"><i class="fa fa-comments"></i> 1</span>
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

import VueStomp from "vue-stomp";
Vue.use(VueStomp,  "http://localhost:8080/ws");

let stompClient = null;

function initListeners(pVueInstance) {

	
}

export default {
	data() {
		return {
			incidences: [],
			incidencesError: null
		};
	},	
	mounted() {
		let self = this;
		return axios.get('http://localhost:8080/incidences2')
			.then(function (response) {
				console.log(response.data)
				self.incidences = response.data;
				initListeners(self);
			})
			.catch(function (error) {
				self.incidencesError = true;
			});
	},
	methods: {

		onConnected(frame){
            console.log('Connected: ' + frame);
			this.$stompClient.subscribe('/topic/incidence', this.responseCallback, this.onFailed);
		},
		responseCallback(frame){
            console.log("responseCallback msg=>" + frame.body);
            let invokeId = frame.body.substr(invokeIdIndex, 4);
            this.removeStompMonitor(invokeId);
		},
		onFailed(frame){
            console.log('Failed: ' + frame);
        },

	}
}


</script>
<style>
</style>
