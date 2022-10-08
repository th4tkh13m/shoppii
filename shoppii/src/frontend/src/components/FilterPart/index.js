import React from 'react'
import ListItem from '@mui/material/ListItem'
import ListItemButton from '@mui/material/ListItemButton'
import ListItemIcon from '@mui/material/ListItemIcon'
import ListItemText from '@mui/material/ListItemText'
import Checkbox from '@mui/material/Checkbox'
import KeyboardArrowDownIcon from '@mui/icons-material/KeyboardArrowDown'
import ExpandLessIcon from '@mui/icons-material/ExpandLess'

import { List, Collapse } from '@mui/material'
import { Storefront, ExpandLess, ExpandMore } from '@mui/icons-material'
import './index.css'

const listProps = [0, 1, 2, 3, 4, 5]
const firstItem = listProps.slice(0, 4)
const remain = listProps.slice(4)

function FilterPart({ title }) {
    const [checked, setChecked] = React.useState([0])
    // const [show, setShow] = React.useState(false)
    const [open, setOpen] = React.useState(false)

    const handleClick = () => {
        setOpen(!open)
    }

    const handleToggle = value => () => {
        const currentIndex = checked.indexOf(value)
        const newChecked = [...checked]

        if (currentIndex === -1) {
            newChecked.push(value)
        } else {
            newChecked.splice(currentIndex, 1)
        }

        setChecked(newChecked)
    }

    return (
        <div className="filter-part">
            <h4>{title}</h4>
            <List
                sx={{
                    width: '100%',
                    maxWidth: 360,
                    bgcolor: 'background.paper',
                }}
                component="nav"
                aria-labelledby="nested-list-subheader"
            >
                {/* first Item */}
                {firstItem.map(value => {
                    const labelId = `checkbox-list-label-${value}`
                    return (
                        <ListItem key={value} disablePadding>
                            <ListItemButton
                                role={undefined}
                                onClick={handleToggle(value)}
                                dense
                            >
                                <ListItemIcon>
                                    <Checkbox
                                        checked={checked.indexOf(value) !== -1}
                                        tabIndex={-1}
                                        disableRipple
                                        inputProps={{
                                            'aria-labelledby': labelId,
                                        }}
                                    />
                                </ListItemIcon>
                                <ListItemText
                                    className="fs-1"
                                    id={labelId}
                                    primary={`Line item ${value + 1}`}
                                />
                            </ListItemButton>
                        </ListItem>
                    )
                })}

                <Collapse in={open} timeout="auto" unmountOnExit>
                    <List component="div" disablePadding>
                        {remain.map(value => {
                            const labelId = `checkbox-list-label-${value}`
                            return (
                                <ListItem key={value} disablePadding>
                                    <ListItemButton
                                        role={undefined}
                                        onClick={handleToggle(value)}
                                        dense
                                    >
                                        <ListItemIcon>
                                            <Checkbox
                                                checked={
                                                    checked.indexOf(value) !==
                                                    -1
                                                }
                                                tabIndex={-1}
                                                disableRipple
                                                inputProps={{
                                                    'aria-labelledby': labelId,
                                                }}
                                            />
                                        </ListItemIcon>
                                        <ListItemText
                                            className="fs-1"
                                            id={labelId}
                                            primary={`Line item ${value + 1}`}
                                        />
                                    </ListItemButton>
                                </ListItem>
                            )
                        })}
                    </List>
                </Collapse>
                <ListItem>
                    <ListItemButton onClick={handleClick}>
                        {open ? <ExpandLess /> : <ExpandMore />}
                    </ListItemButton>
                </ListItem>
            </List>
        </div>
    )
}

export default FilterPart
