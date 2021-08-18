import React, {Component} from 'react';

class Player extends Component {
	constructor(props) {
		super(props);
	}

	render() {
		const current = this.props.current ? ' currentPlayer' : '';
		return (
			<div className={`player ${current}`}>{this.props.player.name}</div>
		);
	}
}

export default Player;