import React, {Component} from 'react';
import Player from './Player';

class Team extends Component {
	constructor(props) {
		super(props);
	}

	render() {
		if (this.props.players) {
			let players = []
			for (let i = 0; i < this.props.players.length; i++) {
				players.push(<Player player={this.props.players[i]}/>);
			}
			return (
				<div className="team">
					{players}
				</div>
			);
		}
		return (
			<h1>TEAM</h1>
		);
	}
}

export default Team;