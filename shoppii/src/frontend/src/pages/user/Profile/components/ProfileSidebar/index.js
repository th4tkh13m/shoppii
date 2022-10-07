import React from 'react'
import './index.css'
import List from '@mui/material/List'
import ListItemButton from '@mui/material/ListItemButton'
import ListItemIcon from '@mui/material/ListItemIcon'
import ListItemText from '@mui/material/ListItemText'
import PersonIcon from '@mui/icons-material/PersonOutlined'
import Collapse from '@mui/material/Collapse'
import ReceiptIcon from '@mui/icons-material/ReceiptOutlined'
import AssignmentIndIcon from '@mui/icons-material/AssignmentIndOutlined'
import VpnKeyIcon from '@mui/icons-material/VpnKeyOutlined'
import HomeIcon from '@mui/icons-material/HomeOutlined'
import ExpandLess from '@mui/icons-material/ExpandLess'
import ExpandMore from '@mui/icons-material/ExpandMore'
import Avatar from '@mui/material/Avatar'
import Stack from '@mui/material/Stack'

function ProfileSidebar() {
    const [open, setOpen] = React.useState(true)

    const handleClick = () => {
        setOpen(!open)
    }

    return (
        <div>
            <Stack direction="row" spacing={2}>
                <Avatar
                    alt="Remy Sharp"
                    src="/static/images/avatar/1.jpg"
                    sx={{ width: 56, height: 56 }}
                />
                <div className="d-flex align-content-center pt-4 fw-bold">Tên người dùng</div>
            </Stack>

            <List
                sx={{
                    width: '100%',
                    maxWidth: 260,
                    bgcolor: 'background.paper',
                }}
                component="nav"
            >
                <ListItemButton onClick={handleClick}>
                    <ListItemIcon>
                        <PersonIcon fontSize="large" color="primary" />
                    </ListItemIcon>
                    <ListItemText primary="Thông tin của tôi" />
                    {open ? <ExpandLess /> : <ExpandMore />}
                </ListItemButton>
                <Collapse in={open} timeout="auto" unmountOnExit>
                    <List component="div" disablePadding>
                        <ListItemButton sx={{ pl: 4 }}>
                            <ListItemIcon>
                                <AssignmentIndIcon
                                    fontSize="large"
                                    color="primary"
                                />
                            </ListItemIcon>
                            <ListItemText primary="Hồ sơ" />
                        </ListItemButton>
                        <ListItemButton sx={{ pl: 4 }}>
                            <ListItemIcon>
                                <HomeIcon fontSize="large" color="primary" />
                            </ListItemIcon>
                            <ListItemText primary="Địa chỉ" />
                        </ListItemButton>
                        <ListItemButton sx={{ pl: 4 }}>
                            <ListItemIcon>
                                <VpnKeyIcon fontSize="large" color="primary" />
                            </ListItemIcon>
                            <ListItemText primary="Đổi mật khẩu" />
                        </ListItemButton>
                    </List>
                </Collapse>

                <ListItemButton>
                    <ListItemIcon>
                        <ReceiptIcon fontSize="large" color="primary" />
                    </ListItemIcon>
                    <ListItemText primary="Lịch sử mua hàng" />
                </ListItemButton>
            </List>
        </div>
    )
}

export default ProfileSidebar
