import React from "react";
import List from '@mui/material/List';
import ListItem from '@mui/material/ListItem';
import ListItemButton from '@mui/material/ListItemButton';
import ListItemIcon from '@mui/material/ListItemIcon';
import ListItemText from '@mui/material/ListItemText';
import Checkbox from '@mui/material/Checkbox';
import KeyboardArrowDownIcon from '@mui/icons-material/KeyboardArrowDown';
import ExpandLessIcon from '@mui/icons-material/ExpandLess';
import './index.css'

function FilterPart({title}) {
  const [checked, setChecked] = React.useState([0]);
  const [show, setShow] = React.useState(false)

  const handleToggle = (value) => () => {
    const currentIndex = checked.indexOf(value);
    const newChecked = [...checked];

    if (currentIndex === -1) {
      newChecked.push(value);
    } else {
      newChecked.splice(currentIndex, 1);
    }

    setChecked(newChecked);
  };


  console.log(show)
  return (
    <div className="filter-part">
    <h4>{title}</h4>
    <List sx={{ width: '100%', maxWidth: 360, bgcolor: 'background.paper' }}>
      {[0, 1, 2, 3, 4, 5].map((value, index) => {
        const labelId = `checkbox-list-label-${value}`;
        return (
          <ListItem 
            key={value}
            disablePadding
          >
            <ListItemButton className={!show && index>3 ? "d-none " : ""} role={undefined} onClick={handleToggle(value)} dense>
              <ListItemIcon>
                <Checkbox
                  checked={checked.indexOf(value) !== -1}
                  tabIndex={-1}
                  disableRipple
                  inputProps={{ 'aria-labelledby': labelId }}
                />
              </ListItemIcon>
              <ListItemText className="fs-1" id={labelId} primary={`Line item ${value + 1}`} />
            </ListItemButton>
          </ListItem>
        );
      })}
      <ListItem>
        <ListItemButton onClick={() => setShow(!show)}>
          {show ? 
            <ExpandLessIcon fontSize="large"></ExpandLessIcon> 
            : 
            <KeyboardArrowDownIcon fontSize="large"></KeyboardArrowDownIcon>
          }
        </ListItemButton>
      </ListItem>
    </List>
    </div>
  );
}

export default FilterPart