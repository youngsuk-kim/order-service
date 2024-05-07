package me.bread.order.application.annotation

import org.springframework.context.annotation.Profile

@Profile("local", "local-dev")
annotation class Local
