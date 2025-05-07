import React, { useState } from 'react';
import axios from 'axios'; // Add this import
import {
  Box,
  TextField,
  Button,
  Stack,
  InputLabel,
  MenuItem,
  FormControl,
  Select,
  Typography,
} from '@mui/material';
import './SearchComp.css';
import ResultsTable from './ResultsTable';
import CircularProgress from '@mui/material/CircularProgress';


function SearchComp() {
  const [domain, setDomain] = useState('');
  const [type, setType] = useState('domain');
  const [result, setResult] = useState(null);
  const [error, setError] = useState(null);
  const [loading, setLoading] = useState(false);


  const handleSubmit = async (event) => {
    event.preventDefault();
    try {
      setLoading(true);
      setError(null);
      const response = await axios.get('http://localhost:8080/api/whois', {
        params: { domain, type }
      });
      
      setResult(response.data);
    } catch (err) {
      setError('Domain lookup failed. Please try again.');
      setResult(null);
    }finally{
      setLoading(false);
    }
  };

  return (
    <>
      <form onSubmit={handleSubmit}>
        <Box sx={{ width: '100%', m: 'auto' }}>
          <TextField
            label="Search Domain Name"
            variant="outlined"
            size="small"
            value={domain}
            onChange={(e) => setDomain(e.target.value)}
            sx={{
              width: '30%',
              m: '50px 0px 0px 150px',
              '& .MuiOutlinedInput-root': {
                borderRadius: '25px',
              },
            }}
          />

          <FormControl sx={{ m: '50px 0px 0px 50px', width: '20%' }}>
            <InputLabel id="type-select-label" sx={{ top: '-5px' }}>
              Type
            </InputLabel>
            <Select
              labelId="type-select-label"
              id="type-select"
              value={type}
              onChange={(e) => setType(e.target.value)}
              label="Type"
              sx={{ borderRadius: '25px' }}
            >
              <MenuItem value={'domain'}>Domain Info</MenuItem>
              <MenuItem value={'contact'}>Contact Info</MenuItem>
            </Select>
          </FormControl>

          <Button
            type="submit"
            variant="outlined"
            sx={{ m: '52px 0px 0px 50px', borderRadius: '25px' }}
          >
            Submit
          </Button>
        </Box>
        {error && <p style={{ color: 'red' }}>{error}</p>}

        {loading && (
          <Box sx={{ display: 'flex', justifyContent: 'center', mt: 8 }}>
            <CircularProgress />
          </Box>
        )}

        {!loading && result && <ResultsTable data={result} />}
      </form>
    </>
  );
}

export default SearchComp;
