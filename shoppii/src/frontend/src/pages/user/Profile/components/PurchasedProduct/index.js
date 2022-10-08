import React from "react";
import { Container, Row, Col } from "react-bootstrap";
import ProductImage from '../../../../../assets/images/bd2e86e454da37f2e6c9a128c8e9a2b8.png'
import './index.css'

function PurchasedProduct() {
    return (
        <div className="product-item">
            <Container fluid="md">
                <Row>
                    <Col md={2} className="d-flex justify-content-end align-content-center">
                        <img src={ProductImage} alt="productimage" className="w-75" />
                    </Col>
                    <Col md={7} className="d-block">
                        <h4>Tên sản phẩm gì gì đó</h4>
                        <div className="product-quantity">X 2</div>
                    </Col>
                    <Col md={3} className="d-flex justify-content-center">
                        <div className="product-price">₫113.000</div>
                    </Col>
                </Row>
            </Container>
        </div>
    )
}

export default PurchasedProduct