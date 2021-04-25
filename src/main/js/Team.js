import React, {Component} from 'react';
import Player from './Player';

class Team extends Component {
	constructor(props) {
		super(props);
	}

	render() {
		const style = {
			color: this.props.team.colorHex
		}
		if (this.props.team.players) {
			let players = []
			for (let i = 0; i < this.props.team.players.length; i++) {
				players.push(
					<Player
						style={style}
						player={this.props.team.players[i]}
						key={this.props.team.players[i].id}
					/>);
			}
			return (
				<div className="team" style={style}>
					<div className="teamName">
						{this.props.team.name}
					</div>
					<div className="teamPlayers">
						{players}
					</div>
				</div>
			);
		}
		return (
			<div className="team" style={style}>
				<div className="teamName">
					{this.props.team.name}
				</div>
				<div className="teamPlayers" />
			</div>
		);
	}
}

export default Team;