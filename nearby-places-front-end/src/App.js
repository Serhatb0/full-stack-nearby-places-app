import './App.css';
import { useState } from 'react';
import axios from 'axios';
import { Map, Marker, GoogleApiWrapper } from 'google-maps-react';
function App({ google }) {
  const [longitude, setLongitude] = useState('');
  const [latitude, setLatitude] = useState('');
  const [radius, setRadius] = useState('');
  const [results, setResults] = useState([]);

  const handleSubmit = async (e) => {
    e.preventDefault();

    try {
      const response = await axios.get(
        `http://localhost:8080/api/places?longitude=${longitude}&latitude=${latitude}&radius=${radius}`
      );

      setResults(response.data.results);
    } catch (error) {
      console.error(error);
    }
  };

  return (
    <div>
      <form onSubmit={handleSubmit}>
        <label>
          Longitude:
          <input
            type="text"
            value={longitude}
            onChange={(e) => setLongitude(e.target.value)}
          />
        </label>
        <br />
        <label>
          Latitude:
          <input
            type="text"
            value={latitude}
            onChange={(e) => setLatitude(e.target.value)}
          />
        </label>
        <br />
        <label>
          Radius:
          <input
            type="text"
            value={radius}
            onChange={(e) => setRadius(e.target.value)}
          />
        </label>
        <br />
        <button type="submit">Search</button>
      </form>

      <ul>
        {results.map((place) => (
          <li key={place.id}>{place.name}</li>
        ))}
      </ul>

      {results.length > 0 && (
        <Map
        google={window.google}
          zoom={10}
          initialCenter={{
            lat: parseFloat(40.7430),
            lng: parseFloat(37.3136)
          }}
          style={{ width: '100%', height: '400px', marginTop: '20px' }}
        >
          {results.map((place) => (
            <Marker
              key={place.id}
              name={place.name}
              position={{
                lat: place.geometry.location.lat,
                lng: place.geometry.location.lng
              }}
            />
          ))}
        </Map>
      )}
    </div>
  );
}

export default GoogleApiWrapper({
  apiKey: 'YOUR APİ KEY'
})(App);
