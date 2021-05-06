import React, {Component} from 'react';
import Board from './Board';
import Team from './Team';
import Player from './Player';
import regeneratorRuntime from "regenerator-runtime";

class Lobby extends Component {
	constructor(props) {
		super(props);
		this.state = props.location.state;
		this.joinTeam = this.joinTeam.bind(this);
	}

	async joinTeam(teamId, playerId) {
		const url = `/api/lobby/${this.state.lobby.id}/${teamId}/${playerId}`;
		const config = {
			method: 'PUT',
			headers: {
				'Accept': 'application/json'
			}
		};
		const response = await fetch(url, config);
		if (response.ok) {
			const lobby = await response.json();
			this.setState({ lobby: lobby });
		}
		else {
			console.log('Error joining team');
			// handle error
		}
	}

	render() {
		if (!this.state.lobby) {
			return (
				<h1>Loading...</h1>
			);
		}
		let undecidedPlayers = []
		if (this.state.lobby.undecidedPlayers) {
			for (let i = 0; i < this.state.lobby.undecidedPlayers.length; i++) {
				undecidedPlayers.push(
					<Player
						current={this.state.currentPlayer.id === this.state.lobby.undecidedPlayers[i].id}
						player={this.state.lobby.undecidedPlayers[i]}
						key={this.state.lobby.undecidedPlayers[i].id}
					/>);
			}
		}
		return (
			<div className="lobby">
				<div className="joinCode">{this.state.lobby.joinCode}</div>
				<div className="lobbyContent">
					<Team team={this.state.lobby.teams[0]} currentPlayer={this.state.currentPlayer} joinTeam={this.joinTeam} />
					<Board words={this.state.lobby.lobbyWords} />
					<Team team={this.state.lobby.teams[1]} currentPlayer={this.state.currentPlayer} joinTeam={this.joinTeam} />
				</div>
				<div className="lobbyUndecidedPlayers">
					<div className="undecided">undecided:</div>
					{undecidedPlayers}
				</div>
			</div>
		)
	}
}

export default Lobby;