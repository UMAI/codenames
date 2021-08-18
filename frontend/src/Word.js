import React, {Component} from 'react';

class Word extends Component {
	constructor(props) {
		super(props);
	}

	render() {
		const style = {
			backgroundColor: this.props.color
		}
		return (
			<button className="word" style={style} onClick={() => alert('click')}>
				{this.props.word}
			</button>
		);
	}
}

export default Word;