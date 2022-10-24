import React from 'react'
import { Container, Row, Col } from 'react-bootstrap'
import { Button, List, Collapse } from '@mui/material'
import {
    Storefront,
    ExpandLess,
    ExpandMore
} from '@mui/icons-material'
import PurchasedProduct from '../PurchasedProduct'
import './index.css'

const listProps = [0,1,2]
const firstItem = listProps.slice(0,1)
const remain = listProps.slice(1)

function OrderHistoryItem() {
    const [open, setOpen] = React.useState(false)

    const handleClick = () => {
        setOpen(!open)
    }

    return (
        <div className="order-history-item my-5">
            <Container fluid="md">
                <Row className="d-flex mb-1 order-header py-3">
                    <Col md={2}>
                        <div className="fs-5 fw-bold mt-2 ms-3">Tên cửa hàng</div>
                    </Col>
                    <Col md={7}>
                        <Button
                            variant="outlined"
                            startIcon={<Storefront />}
                            size="small"
                            className="view-shop-btn"
                            sx={{ color: 'white',  borderColor:'white'}}
                        >
                            Xem cửa hàng
                        </Button>
                    </Col>
                    <Col md={3}>
                        <div className="fs-5 mt-2 text-left">
                            Ngày mua : 20-08-2002
                        </div>
                    </Col>
                </Row>
                <Row>
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
                        <PurchasedProduct/>
                        
                        <Collapse in={open} timeout="auto" unmountOnExit>
                            <List component="div" disablePadding>
                                {remain.map((item) => <PurchasedProduct/>)}
                            </List>
                        </Collapse>
                    </List>
                </Row>
                <Row>
                    <Col
                        md={12}
                        className="d-flex justify-content-center align-content-center show-btn"
                        onClick={handleClick}
                    >
                        {open ? <ExpandLess /> : <ExpandMore />}
                    </Col>
                </Row>
                <Row>
                    <Col md={12}>
                        <div className="order-footer pb-3">
                            <span>Tổng tiền : </span>
                            ₫168.500
                        </div>
                    </Col>
                </Row>
            </Container>
        </div>
    )
}

export default OrderHistoryItem
