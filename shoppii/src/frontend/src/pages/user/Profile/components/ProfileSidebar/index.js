import React from 'react'
import './index.css'
import {
    List,
    ListItemButton,
    ListItemIcon,
    ListItemText,
    Collapse,
    Avatar,
    Stack,
} from '@mui/material'
import {
    PersonOutline,
    ReceiptOutlined,
    AssignmentIndOutlined,
    VpnKeyOutlined,
    HomeOutlined,
    ExpandLess,
    ExpandMore,
} from '@mui/icons-material'

export const changePass = 'Change Password'
export const editPro = 'Edit Profile'
export const orderHistory = 'Order History'
export const addressList = 'Address'

function ProfileSidebar({ getAction }) {
    const sendAction = action => {
        getAction(action)
    }
    const [open, setOpen] = React.useState(true)

    const handleClick = () => {
        setOpen(!open)
    }

    return (
        <div className="w-80">
            <Stack direction="row" spacing={2}>
                <Avatar
                    alt="Remy Sharp"
                    src="/static/images/avatar/1.jpg"
                    sx={{ width: 56, height: 56 }}
                />
                <div className="d-flex align-content-center pt-4 fw-bold">
                    Tên người dùng
                </div>
            </Stack>

            <List
                sx={{
                    width: '100%',
                    maxWidth: 260,
                    bgcolor: 'background.paper',
                }}
                className="list-sidebar"
                component="nav"
            >
                <ListItemButton onClick={handleClick}>
                    <ListItemIcon>
                        <PersonOutline fontSize="large" color="primary" />
                    </ListItemIcon>
                    <ListItemText primary="Thông tin của tôi" />
                    {open ? <ExpandLess /> : <ExpandMore />}
                </ListItemButton>
                <Collapse in={open} timeout="auto" unmountOnExit>
                    <List component="div" disablePadding>
                        <ListItemButton
                            sx={{ pl: 4 }}
                            onClick={() => sendAction(editPro)}
                        >
                            <ListItemIcon>
                                <AssignmentIndOutlined
                                    fontSize="large"
                                    color="primary"
                                />
                            </ListItemIcon>
                            <ListItemText primary="Hồ sơ" />
                        </ListItemButton>
                        <ListItemButton
                            sx={{ pl: 4 }}
                            onClick={() => sendAction(addressList)}
                        >
                            <ListItemIcon>
                                <HomeOutlined fontSize="large" color="primary" />
                            </ListItemIcon>
                            <ListItemText primary="Địa chỉ" />
                        </ListItemButton>
                        <ListItemButton
                            sx={{ pl: 4 }}
                            onClick={() => sendAction(changePass)}
                        >
                            <ListItemIcon>
                                <VpnKeyOutlined fontSize="large" color="primary" />
                            </ListItemIcon>
                            <ListItemText primary="Đổi mật khẩu" />
                        </ListItemButton>
                    </List>
                </Collapse>

                <ListItemButton onClick={() => sendAction(orderHistory)}>
                    <ListItemIcon>
                        <ReceiptOutlined fontSize="large" color="primary" />
                    </ListItemIcon>
                    <ListItemText primary="Lịch sử mua hàng" />
                </ListItemButton>
            </List>
        </div>
    )
}

export default ProfileSidebar
