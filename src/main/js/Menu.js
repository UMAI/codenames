import React, {Component} from 'react';
import {Link} from 'react-router-dom';

class Menu extends Component {
	constructor(props) {
		super(props);
		this.state = {
			name: this.generatePlayerName(),
			joinLobbyLink: null
		};
		this.handleJoinLobbyLinkChange = this.handleJoinLobbyLinkChange.bind(this);
	}

	generatePlayerName() {
		return 'name';
	}

	handleJoinLobbyLinkChange(event) {
		console.log('in handleJoinLobbyLinkChange now');
		const link = '/lobby/?joinCode=' + event.target.value + '&playerName=' + this.state.name;
		this.setState({joinLobbyLink: link});
		console.log(this.state);
	}

	render() {
		return (
			<ul className="menu">
				<li contentEditable="true">{this.state.name}</li>
				<li>
					<Link to={'/lobby?creatorName=' + this.state.name}>CREATE LOBBY</Link>
				</li>
				<li>
					<input type="text" id="join_code" onChange={this.handleJoinLobbyLinkChange} />
					<Link to={this.state.joinLobbyLink}>JOIN</Link>
				</li>
			</ul>
		);
	}
}

export default Menu;