import React from 'react';

const Wall = ({ wall }) => {
    const style = {
        position: 'absolute',
        left: wall.x,
        top: wall.y,
        width: wall.width,
        height: wall.length,
        transform: `rotate(${wall.rotate}deg)`,
        transformOrigin: 'top left',
        backgroundColor: 'black',
    };

    return <div style={style}/>;
};

export default Wall;

