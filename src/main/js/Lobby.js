import React, {Component} from 'react';
import Board from './Board';
import Team from './Team';

class Lobby extends Component {
	constructor(props) {
		super(props);
		this.state = {
			lobby: null
		};
	}

	getQuery() {
		return new URLSearchParams(this.props.location.search);
	}

	componentDidMount() {
		let query = this.getQuery();
		const creatorName = query.get('creatorName');
		if (creatorName) {
			fetch('/api/lobby/create?creatorName=' + creatorName + '&langCode=en', {method: 'POST'})
				.then(response => response.json())
				.then(data => this.setState({lobby: data}));
		}
		else {
			fetch('/api/lobby/join?joinCode=' + query.get('joinCode') + '&playerName=' + query.get('playerName'), {method: 'POST'})
				.then(response => response.json())
				.then(data => this.setState({lobby: data}));
		}
	}

	render() {
		if (!this.state.lobby) {
			return (
				<h1>Loading...</h1>
			);
		}
		return (
			<div className="lobby">
				<h1>{this.state.lobby.joinCode}</h1>
				<Team players={this.state.lobby.teams[0].players} />
				<Board words={this.state.lobby.lobbyWords} />
				<Team players={this.state.lobby.teams[1].players} />
			</div>
		)
	}
}

export default Lobby;