package br.com.fiap.smartweather.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowColumn
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.*
import br.com.fiap.smartweather.ui.theme.*

@OptIn(ExperimentalMaterial3Api::class, ExperimentalLayoutApi::class)
@Composable
fun SettingScreen() {
    // Style Colors
    val styleColorScheme = MaterialTheme.colorScheme
    val textZinc50 = styleColorScheme.onPrimary
    val textZinc600 = styleColorScheme.onSecondary
    val onBackground = styleColorScheme.onBackground
    val backgroundZinc900 = styleColorScheme.background

    // Style fonts
    val styleFontLarge = MaterialTheme.typography.titleLarge

    Scaffold(
        topBar = {
            TopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = backgroundZinc900,
                ),
                title = {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(52.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        IconButton(
                            onClick = { /*TODO*/ },
                        ) {
                            Image(
                                imageVector = Icons.Default.KeyboardArrowLeft,
                                contentDescription = null,
                                contentScale = ContentScale.Crop,
                                colorFilter = ColorFilter.tint(Violet600)
                            )
                        }
                        Text(
                            text = "Configurações",
                            color = textZinc50
                        )
                    }
                },
            )
        },

        ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .verticalScroll(rememberScrollState())
                .padding(12.dp),
            verticalArrangement = Arrangement.run { spacedBy(12.dp) },
        ) {
            // Campos de Usuário
            Column {
                /*Image(painter = , contentDescription = )*/
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        text = "Arandas",
                        fontSize = styleFontLarge.fontSize,
                        fontWeight = styleFontLarge.fontWeight
                    )
                    IconButton(
                        onClick = { /*TODO*/ }
                    ) {
                        Image(
                            imageVector = Icons.Default.Edit,
                            contentDescription = null,
                            colorFilter = ColorFilter.tint(Violet600),
                            modifier = Modifier
                                .size(24.dp)
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.size(2.dp))

            // Campo unidade de temperatura
            Column {
                // Estado para rastrear qual botão está ativo
                var selectedButton by remember { mutableStateOf("Celsius") }

                Text(
                    text = "Unidades de temperatura",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Light,
                    color = textZinc600
                )

                Spacer(modifier = Modifier.height(4.dp))

                FlowRow(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(64.dp)  // Substitua o height por fillMaxHeight
                        .background(
                            onBackground,
                            shape = RoundedCornerShape(12.dp)
                        )
                        .wrapContentSize(Alignment.Center)
                        .padding(6.dp),
                    verticalArrangement = Arrangement.Center,
                    horizontalArrangement = Arrangement.spacedBy(6.dp)
                ) {
                    Button(
                        onClick = {
                            // Atualiza o estado quando o botão é clicado
                            selectedButton = "Celsius"
                        },
                        modifier = Modifier
                            .fillMaxHeight()
                            .weight(1f),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = if (selectedButton == "Celsius") Violet600 else onBackground
                        ),
                        shape = RoundedCornerShape(8.dp)
                    ) {
                        Text(
                            text = "Celsius",
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Light
                        )
                    }

                    Button(
                        onClick = {
                            // Atualiza o estado quando o botão é clicado
                            selectedButton = "Fahrenheit"
                        },
                        modifier = Modifier
                            .fillMaxHeight()
                            .weight(1f),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = if (selectedButton == "Fahrenheit") Violet600 else onBackground
                        ),
                        shape = RoundedCornerShape(8.dp)
                    ) {
                        Text(
                            text = "Fahrenheit",
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Light
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.size(4.dp))

            // Notificações
            Column {
                Text(
                    text = "Notificações",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Light,
                    color = textZinc600
                )

                Spacer(modifier = Modifier.height(4.dp))

                FlowColumn(
                    modifier = Modifier
                        .fillMaxWidth(),
                    verticalArrangement = Arrangement.spacedBy(6.dp),
                ) {
                    // Receber notificações
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(88.dp)
                            .background(
                                onBackground,
                                shape = RoundedCornerShape(12.dp)
                            )
                            .wrapContentSize(Alignment.Center)
                            .padding(6.dp),
                        verticalArrangement = Arrangement.SpaceBetween
                    ) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text(
                                text = "Receber notificações",
                                fontSize = 18.sp,
                                fontWeight = FontWeight.Light,
                                letterSpacing = 1.sp
                            )
                            Text("Switch")
                        }

                        Spacer(modifier = Modifier.size(4.dp))

                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text(
                                text = "Permite o envio das notificações abaixo, caso este setor esteja ativo. ",
                                fontSize = 14.sp,
                                color = Zinc500
                            )
                        }
                    }
                    // Mudanças Climáticas
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(88.dp)
                            .background(
                                onBackground,
                                shape = RoundedCornerShape(12.dp)
                            )
                            .wrapContentSize(Alignment.Center)
                            .padding(6.dp),
                        verticalArrangement = Arrangement.SpaceBetween
                    ) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text(
                                text = "Mudanças Climáticas",
                                fontSize = 18.sp,
                                fontWeight = FontWeight.Light,
                                letterSpacing = 1.sp
                            )
                            Text("Switch")
                        }

                        Spacer(modifier = Modifier.size(4.dp))

                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text(
                                text = "Aviso sobre mudança climática na região, para que o usuário se adéque as condições climáticas.",
                                fontSize = 14.sp,
                                color = Zinc500
                            )
                        }
                    }
                    // Recomendações diárias
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(88.dp)
                            .background(
                                onBackground,
                                shape = RoundedCornerShape(12.dp)
                            )
                            .wrapContentSize(Alignment.Center)
                            .padding(6.dp),
                        verticalArrangement = Arrangement.SpaceBetween
                    ) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text(
                                text = "Recomedações diárias",
                                fontSize = 18.sp,
                                fontWeight = FontWeight.Light,
                                letterSpacing = 1.sp
                            )
                            Text("Switch")
                        }

                        Spacer(modifier = Modifier.size(4.dp))

                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text(
                                text = "Recomendações de vestes, protetores ou guarda-chuvas, para evitar imprevistos ou incidentes.",
                                fontSize = 14.sp,
                                color = Zinc500
                            )
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.size(4.dp))

            // Campo Atualização de Previsão
            Column {
                Text(
                    text = "Atualização de previsão",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Light,
                    color = textZinc600
                )

                Spacer(modifier = Modifier.height(4.dp))

                FlowColumn(
                    modifier = Modifier
                        .fillMaxWidth(),
                    verticalArrangement = Arrangement.spacedBy(6.dp),
                ) {
                    // Receber notificações
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(88.dp)
                            .background(
                                onBackground,
                                shape = RoundedCornerShape(12.dp)
                            )
                            .wrapContentSize(Alignment.Center)
                            .padding(6.dp),
                        verticalArrangement = Arrangement.SpaceBetween
                    ) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text(
                                text = "Frequência de atualização",
                                fontSize = 18.sp,
                                fontWeight = FontWeight.Light,
                                letterSpacing = 1.sp
                            )
                            Text("Switch")
                        }

                        Spacer(modifier = Modifier.size(4.dp))

                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text(
                                text = "Tempo que o aplicativo deve atualizar as informações climáticas, por padrão, usamos 15 minutos.",
                                color = Zinc500,
                                fontSize = 14.sp
                            )
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.size(4.dp))

            // Campo Temas do Aplicativo
            Column {
                // Estado para rastrear qual botão está ativo
                var selectedButton by remember { mutableStateOf("Sistema") }

                Text(
                    text = "Temas do aplicativo (em breve)",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Light,
                    color = textZinc600
                )

                Spacer(modifier = Modifier.height(4.dp))

                FlowRow(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(64.dp)  // Substitua o height por fillMaxHeight
                        .background(
                            onBackground,
                            shape = RoundedCornerShape(12.dp)
                        )
                        .wrapContentSize(Alignment.Center)
                        .padding(6.dp),
                    verticalArrangement = Arrangement.Center,
                    horizontalArrangement = Arrangement.spacedBy(6.dp)
                ) {
                    Button(
                        onClick = {
                            // Atualiza o estado quando o botão é clicado
                            selectedButton = "Sistema"
                        },
                        modifier = Modifier
                            .fillMaxHeight()
                            .weight(1f),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = if (selectedButton == "Sistema") Violet600 else onBackground
                        ),
                        shape = RoundedCornerShape(8.dp)
                    ) {
                        Text(
                            text = "Sistema",
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Light
                        )
                    }

                    Button(
                        onClick = {
                            // Atualiza o estado quando o botão é clicado
                            selectedButton = "Escuro"
                        },
                        modifier = Modifier
                            .fillMaxHeight()
                            .weight(1f),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = if (selectedButton == "Escuro") Violet600 else onBackground
                        ),
                        shape = RoundedCornerShape(8.dp)
                    ) {
                        Text(
                            text = "Escuro",
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Light
                        )
                    }

                    Button(
                        onClick = {
                            // Atualiza o estado quando o botão é clicado
                            selectedButton = "Claro"
                        },
                        modifier = Modifier
                            .fillMaxHeight()
                            .weight(1f),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = if (selectedButton == "Claro") Violet600 else onBackground
                        ),
                        shape = RoundedCornerShape(8.dp)
                    ) {
                        Text(
                            text = "Claro",
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Light
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.size(4.dp))

            // Campo Idiomas do Aplicativo
            Column {
                Text(
                    text = "Idiomas do aplicativo (em breve)",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Light,
                    color = textZinc600
                )

                Spacer(modifier = Modifier.height(4.dp))

                FlowColumn(
                    modifier = Modifier
                        .fillMaxWidth(),
                    verticalArrangement = Arrangement.spacedBy(6.dp),
                ) {
                    // Receber notificações
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(108.dp)
                            .background(
                                onBackground,
                                shape = RoundedCornerShape(12.dp)
                            )
                            .wrapContentSize(Alignment.Center)
                            .padding(6.dp),
                        verticalArrangement = Arrangement.SpaceBetween
                    ) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text(
                                text = "Idiomas do aplicativo",
                                fontSize = 18.sp,
                                fontWeight = FontWeight.Light,
                                letterSpacing = 1.sp
                            )
                            Text("Switch")
                        }

                        Spacer(modifier = Modifier.size(4.dp))

                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text(
                                text = "Idioma que o aplicativo deve utilizar em todas as telas, se adequando ao idioma do usuário. Por padrão, utilizamos Português / BR.",
                                color = Zinc500,
                                fontSize = 14.sp
                            )
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.size(4.dp))

            // Campo Resete do Aplicativo
            Column {
                Text(
                    text = "Configurações do aplicativo",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Light,
                    color = textZinc600
                )

                Spacer(modifier = Modifier.height(4.dp))

                FlowColumn(
                    modifier = Modifier
                        .fillMaxWidth(),
                    verticalArrangement = Arrangement.spacedBy(6.dp),
                ) {
                    // Receber notificações
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(88.dp)
                            .background(
                                onBackground,
                                shape = RoundedCornerShape(12.dp)
                            )
                            .wrapContentSize(Alignment.Center)
                            .padding(6.dp),
                        verticalArrangement = Arrangement.SpaceBetween
                    ) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text(
                                text = "Resetar configurações",
                                fontSize = 18.sp,
                                fontWeight = FontWeight.Light,
                                letterSpacing = 1.sp
                            )
                            Text("Switch")
                        }

                        Spacer(modifier = Modifier.size(4.dp))

                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text(
                                text ="Irá reconfigurar padrões estabelecidos pelo usuário para configurações padrões definidas pelo aplicativo.",
                                color = Zinc500,
                                fontSize = 14.sp
                            )
                        }
                    }
                }
            }
        }
    }
}