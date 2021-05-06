import React, {Component} from 'react';
import Player from './Player';

class Team extends Component {
	constructor(props) {
		super(props);
		this.joinTeam = this.joinTeam.bind(this);
	}

	joinTeam() {
		if (this.props.currentPlayer.teamId !== this.props.team.id) {
			this.props.joinTeam(this.props.team.id, this.props.currentPlayer.id);
		}
	}

	render() {
		const style = {
			color: this.props.team.colorHex
		}
		let players = []
		if (this.props.team.players) {
			for (let i = 0; i < this.props.team.players.length; i++) {
				players.push(
					<Player
						style={style}
						current={this.props.currentPlayer.id === this.props.team.players[i].id}
						player={this.props.team.players[i]}
						key={this.props.team.players[i].id}
					/>);
			}
		}
		return (
			<div className="team" style={style} onClick={this.joinTeam}>
				<div className="teamName">
					{this.props.team.name}
				</div>
				<div className="teamPlayers">
					{players}
				</div>
			</div>
		);
	}
}

export default Team;