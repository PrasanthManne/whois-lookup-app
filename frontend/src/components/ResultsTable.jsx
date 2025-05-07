import React from 'react';
import {
  Table,
  TableBody,
  TableCell,
  TableContainer,
  TableHead,
  TableRow,
  Paper,
  Typography,
} from '@mui/material';

function ResultsTable({ data }) {
  if (!data || Object.keys(data).length === 0) {
    return null;
  }

  return (
    <TableContainer
      component={Paper}
      sx={{
        width: '80%',
        margin: '30px auto',
        borderRadius: '20px',
        boxShadow: 3,
        backgroundColor: '#fdfdfd',
      }}
    >
      <Typography
        variant="h6"
        sx={{ padding: '15px', textAlign: 'center', fontWeight: 'bold' }}
      >
        Lookup Result
      </Typography>
      <Table>
        <TableHead>
          <TableRow>
            <TableCell
              sx={{ fontWeight: 'bold', backgroundColor: '#f0f0f0' }}
              align="left"
            >
              Field
            </TableCell>
            <TableCell
              sx={{ fontWeight: 'bold', backgroundColor: '#f0f0f0' }}
              align="left"
            >
              Value
            </TableCell>
          </TableRow>
        </TableHead>
        <TableBody>
          {Object.entries(data).map(([key, value]) => (
            <TableRow key={key}>
              <TableCell sx={{ textTransform: 'capitalize' }}>{key}</TableCell>
              <TableCell>{value || 'N/A'}</TableCell>
            </TableRow>
          ))}
        </TableBody>
      </Table>
    </TableContainer>
  );
}

export default ResultsTable;
