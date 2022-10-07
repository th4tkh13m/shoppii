import React from 'react'
import './index.css'
import Product from '../../assets/images/bd2e86e454da37f2e6c9a128c8e9a2b8.png'
import { styled } from '@mui/material/styles'
import Card from '@mui/material/Card'
import CardActions from '@mui/material/CardActions'
import CardContent from '@mui/material/CardContent'
import CardMedia from '@mui/material/CardMedia'
import Button from '@mui/material/Button'
import AddShoppingCartIcon from '@mui/icons-material/AddShoppingCart';
import Typography from '@mui/material/Typography'

function ProductCard() {
    return (
        <div className="product-card">
            <Card sx={{ maxWidth: 200 }}>
                <CardMedia
                    component="img"
                    height="200"
                    image={Product}
                    alt="green iguana"
                />
                <CardContent>
                    <Typography gutterBottom variant="h6" component="div">
                        Lizard
                    </Typography>
                    <Typography variant="body2" color="text.secondary">
                    </Typography>
                </CardContent>
                <CardActions className='d-flex justify-content-between'>
                    <Typography gutterBottom variant="5" component="div">
                        100.000
                    </Typography>
                    <AddShoppingCartIcon fontSize='large'></AddShoppingCartIcon>
                </CardActions>
            </Card>
        </div>
    )
}

export default ProductCard
