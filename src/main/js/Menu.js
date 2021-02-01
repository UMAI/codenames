import React, {Component} from 'react';
import {Link} from 'react-router-dom';

class Menu extends Component {
	constructor(props) {
		super(props);
		this.state = {
			name: this.generatePlayerName(),
			joinLobbyLink: null
		};
	}

	generatePlayerName() {
		return 'name';
	}

	setJoinLobbyLink() {
		const link = '/lobby/?joinCode=' + document.getElementById('join_code').value + '?playerName=' + this.state.name;
		this.setState({joinLobbyLink: link});
	}

	render() {
		return (
			<ul className="menu">
				<li contentEditable="true">{this.state.name}</li>
				<li>
					<Link to={'/lobby?creatorName=' + this.state.name}>CREATE LOBBY</Link>
				</li>
				<li>
					<input id="join_code" onChange={() => this.setJoinLobbyLink} />
					<Link to={this.state.joinLobbyLink}>JOIN</Link>
				</li>
			</ul>
		);
	}
}

export default Menu;