import React, {Component} from 'react';
import Board from './Board';
import Team from './Team';
import Player from './Player';

class Lobby extends Component {
	constructor(props) {
		super(props);
		this.state = props.location.state;
	}

	render() {
		if (!this.state.lobby) {
			return (
				<h1>Loading...</h1>
			);
		}
		console.log(this.state.lobby);
		let undecidedPlayers = []
		if (this.state.lobby.undecidedPlayers) {
			for (let i = 0; i < this.state.lobby.undecidedPlayers.length; i++) {
				undecidedPlayers.push(
					<Player
						current={this.state.name === this.state.lobby.undecidedPlayers[i].name}
						player={this.state.lobby.undecidedPlayers[i]}
						key={this.state.lobby.undecidedPlayers[i].id}
					/>);
			}
		}
		return (
			<div className="lobby">
				<div className="joinCode">{this.state.lobby.joinCode}</div>
				<div className="lobbyContent">
					<Team team={this.state.lobby.teams[0]} />
					<Board words={this.state.lobby.lobbyWords} />
					<Team team={this.state.lobby.teams[1]} />
				</div>
				<div className="lobbyUndecidedPlayers">
					<h1>undecided:</h1>
					{undecidedPlayers}
				</div>
			</div>
		)
	}
}

export default Lobby;