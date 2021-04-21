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
			fetch('/api/lobby?creatorName=' + creatorName + '&langCode=en', {method: 'PUT'})
				.then(response => response.json())
				.then(data => this.setState({lobby: data}));
		}
		else {
			fetch('/api/lobby/join?joinCode=' + query.get('joinCode') + '&playerName=' + query.get('playerName'), {method: 'POST'})
				.then(response => response.json())
				.then(data => this.setState({lobby: data}));
		}
		this.setupBeforeUnloadListener();
	}

	setupBeforeUnloadListener() {
		window.addEventListener("beforeunload", (event) => {
			fetch('/api/lobby/' + this.state.lobby.id, {method: 'DELETE'});
		})
	}

	render() {
		if (!this.state.lobby) {
			return (
				<h1>Loading...</h1>
			);
		}
		return (
			<div className="lobby">
				<div className="joinCode">{this.state.lobby.joinCode}</div>
				<div className="lobbyContent">
					<Team team={this.state.lobby.teams[0]} />
					<Board words={this.state.lobby.lobbyWords} />
					<Team team={this.state.lobby.teams[1]} />
				</div>
			</div>
		)
	}
}

export default Lobby;