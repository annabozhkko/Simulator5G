import React, {useEffect, useRef, useState} from 'react';
import axios from 'axios';
import Wall from "./Wall";

const BLOCK_NUMBER = 2;
const FIELD_SIZE = 740;

const App = () => {
    const [walls, setWalls] = useState([]);
    const [antennas, setAntennas] = useState([]);
    const [addingAntenna, setAddingAntenna] = useState(false);
    const [direction, setDirection] = useState(0);
    const [blockPowers, setBlockPowers] = useState([]);
    const [loading, setLoading] = useState(false);

    const canvasRef = useRef(null);

    useEffect(() => {
        const fetchData = async () => {
            try {
                const response = await axios.get('http://localhost:8080/api/walls');
                setWalls(response.data);
            } catch (error) {
                console.error(error);
            }
        };
        fetchData();
    }, []);

    useEffect(() => {
        fillCanvas();
    }, [blockPowers]);

    const updateCanvas = async () => {
        setLoading(true);
        try {
            const powers = await Promise.all(
                Array.from({length: BLOCK_NUMBER}, (_, i) =>
                    axios.get(`http://localhost:8080/api/block-power/${i}`)
                )
            );
            const allPowers = powers.flatMap(response => response.data.power);
            setBlockPowers(allPowers);
        } catch (error) {
            console.error(error);
        }
    }

    const fillCanvas = () => {
        const canvas = canvasRef.current;
        const context = canvas.getContext('2d');

        canvas.width = FIELD_SIZE;
        canvas.height = FIELD_SIZE;

        console.log(blockPowers);

        for (let x = 0; x < FIELD_SIZE; x++) {
            for (let y = 0; y < FIELD_SIZE; y++) {
                context.fillStyle = getColorFromPower(blockPowers[x * FIELD_SIZE + y]);
                context.fillRect(x, y, 1, 1);
            }
        }
        setLoading(false);
    }

    const handleAddingAntenna = async (e) => {
        if (!addingAntenna) return;

        const {offsetX, offsetY} = e.nativeEvent;

        const newAntenna = {
            x: offsetX,
            y: offsetY,
            direction: direction
        };

        try {
            await axios.post('http://localhost:8080/api/antenna', newAntenna);
            setAntennas([...antennas, newAntenna]);
        } catch (error) {
            console.error(error);
        }
    };

    const toggleAddingAntenna = () => {
        if (addingAntenna) {
            updateCanvas();
        }
        setAddingAntenna(!addingAntenna);
    };

    const handleDirectionChange = (e) => {
        setDirection(parseInt(e.target.value));
    };

    const handleDeletingAntenna = async (index) => {
        setAntennas(antennas.filter((a, i) => i !== index));
        try {
            await axios.delete(`http://localhost:8080/api/antenna/${index}`);
        } catch (error) {
            console.error(error);
        }
    }

    const getColorFromPower = (power) => {
        if (power === 0) return 'blue'
        else if (power > -40) return 'red';
        else if (power > -45) return 'darkorange';
        else if (power > -54) return 'orange';
        else if (power > -78) return 'yellow';
        else if (power > -96) return 'greenyellow';
        else if (power > -106) return 'cornflowerblue';
        else return 'blue';
    };

    return (
        <div>
            <h1>Simulator 5G</h1>
            <button onClick={toggleAddingAntenna}>{addingAntenna ? 'Отменить редактирование' : 'Редактировать конфигурацию'}</button>
            {addingAntenna && (
                <div>
                    <label>
                        Направление(градусы):
                        <input type="number" value={direction} onChange={handleDirectionChange} />
                        Нажмите дважды чтобы добавить антенну
                    </label>
                </div>
            )}

            <div style={{ position: 'relative', width: '740px', height: '740px', border: '1px solid black', marginRight: '20px' }} onDoubleClick={handleAddingAntenna}>
                {walls.map((wall, index) => (
                    <Wall key={index} wall={wall} />
                ))}

                {antennas.map((antenna, index) => (
                    <div key={index} style={{ position: 'absolute', left: antenna.x, top: antenna.y }}>
                        <div style={{ width: '10px', height: '10px', borderRadius: '50%', backgroundColor: 'purple' }} />
                        {addingAntenna && (
                            <button onClick={() => handleDeletingAntenna(index)}>Удалить</button>
                        )}
                    </div>
                ))}

                <canvas ref={canvasRef} />

                {loading && (
                    <div style={{
                        position: 'absolute',
                        top: '0',
                        left: '0',
                        width: '100%',
                        height: '100%',
                        backgroundColor: 'rgba(255, 255, 255, 0.5)',
                        display: 'flex',
                        justifyContent: 'center',
                        alignItems: 'center'
                    }}>
                        Loading...
                    </div>
                )}
            </div>
        </div>
    );
};

export default App;
