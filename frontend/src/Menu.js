import React, {Component} from 'react';
import {Link, Redirect} from 'react-router-dom';

class Menu extends Component {
	constructor(props) {
		super(props);
		this.state = {
			name: this.generatePlayerName(),
			lobby: null,
			joinCode: null,
			redirect: false
		};
		this.setNewLobbyURL = this.setNewLobbyURL.bind(this);
		this.setJoinLobbyURL = this.setJoinLobbyURL.bind(this);
	}

	generatePlayerName() {
		return 'Josh';
	}

	setNewLobbyURL() {
		fetch('/api/lobby?creatorName=' + this.state.name + '&langCode=en', {method: 'PUT'})
			.then(response => response.json())
			.then(data => this.setState({lobby: data, redirect: true}));
	}

	setJoinLobbyURL() {
		fetch('/api/lobby?joinCode=' + this.state.joinCode + '&playerName=' + this.state.name, {method: 'POST'})
			.then(response => response.json())
			.then(data => this.setState({lobby: data, redirect: true}));
	}

	render() {
		if (this.state.redirect) {
			const currentPlayer = this.state.lobby.undecidedPlayers.find(player => player.name === this.state.name)
			return (
				<Redirect to={{
					pathname: '/lobby/' + this.state.lobby.id,
					state: {
						lobby: this.state.lobby,
						currentPlayer: currentPlayer
					}
				}} />
			)
		}
		return (
			<div className="menu">
				<div className="menuItem">
					<input type="text" value={this.state.name} onChange={event => this.setState({name: event.target.value})} />
				</div>
				<div className="menuItem">
					<button onClick={this.setNewLobbyURL}>CREATE LOBBY</button>
				</div>
				<div className="menuItem">
					<input type="text" onChange={event => this.setState({joinCode: event.target.value})} />
					<button onClick={this.setJoinLobbyURL}>JOIN</button>
				</div>
			</div>
		);
	}
}

export default Menu;