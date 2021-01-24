import React, {Component} from 'react';

class Word extends Component {
	constructor(props) {
		super(props);
	}

	render() {
		return (
			<button className="word" onClick={() => alert('click')}>
				{this.props.word}
			</button>
		);
	}
}

export default Word;