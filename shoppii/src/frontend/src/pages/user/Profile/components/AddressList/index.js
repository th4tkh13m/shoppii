import React from 'react'
import './index.css'
import AddressItem from '../Address'
import AddOutlinedIcon from '@mui/icons-material/AddOutlined'
import Box from '@mui/material/Box'

function AddressList() {
    return (
        <div className="address-list">
            <Box
                className="add-new-address d-flex justify-content-center"
                component="span"
                sx={{ p: 2, border: '1px dashed grey' }}
            >
                <AddOutlinedIcon sx={{ fontSize: '25px', color: 'gray' }} />
                <div className="ms-3 pt-1">THÊM ĐỊA CHỈ MỚI</div>
            </Box>
            {[0, 1, 2].map(item => (
                <AddressItem />
            ))}
        </div>
    )
}

export default AddressList
